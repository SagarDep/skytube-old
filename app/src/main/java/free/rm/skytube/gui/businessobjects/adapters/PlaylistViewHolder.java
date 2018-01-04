/*
 * SkyTube
 * Copyright (C) 2017  Ramon Mifsud
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation (version 3 of the License).
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package free.rm.skytube.gui.businessobjects.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import butterknife.BindView;
import butterknife.ButterKnife;
import free.rm.skytube.R;
import free.rm.skytube.businessobjects.YouTube.POJOs.YouTubePlaylist;
import free.rm.skytube.gui.businessobjects.PlaylistClickListener;

/**
 * A ViewHolder for the playlists grid view.
 */
public class PlaylistViewHolder extends RecyclerView.ViewHolder {
	@BindView(R.id.playlistViewLayout)
	View playlistViewLayout;
	@BindView(R.id.thumbnail_image_view)
	ImageView thumbnailImageView;
	@BindView(R.id.title_text_view)
	TextView titleTextView;
	@BindView(R.id.playlist_video_count_textview)
	TextView videoCountTextView;
	@BindView(R.id.publish_date_text_view)
	TextView publishDateTextView;

	private PlaylistClickListener playlistClickListener;

	public PlaylistViewHolder(View view, PlaylistClickListener playlistClickListener) {
		super(view);
		ButterKnife.bind(this, view);
		this.playlistClickListener = playlistClickListener;
	}

	protected void setPlaylist(final YouTubePlaylist playlist, Context context) {
		Glide.with(context)
						.load(playlist.getThumbnailUrl())
						.apply(new RequestOptions().placeholder(R.drawable.thumbnail_default))
						.into(thumbnailImageView);
		titleTextView.setText(playlist.getTitle());
		publishDateTextView.setText(playlist.getPublishDatePretty());
		videoCountTextView.setText(String.format(context.getString(R.string.num_videos), playlist.getVideoCount()));
		playlistViewLayout.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				playlistClickListener.onClickPlaylist(playlist);
			}
		});
	}
}